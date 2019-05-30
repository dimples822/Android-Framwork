package com.dimples.mvp.apt;

import com.dimples.annotation.MvpEmptyViewFactory;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;

/**
 * @author zhongyj
 * @date 2019/5/15
 */
@AutoService(Processor.class)
public class MvpProccesor extends AbstractProcessor {

    private static final String CLASS_NAME = "MvpEmptyViewFactory";

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(MvpEmptyViewFactory.class.getCanonicalName());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        MethodSpec.Builder mMethod = MethodSpec.methodBuilder("create")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(Object.class)
                .addParameter(Class.class, "mClass")
                .addException(IllegalAccessException.class)
                .addException(InstantiationException.class);

        TypeSpec.Builder mClass = TypeSpec.classBuilder(CLASS_NAME)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addJavadoc("empty view by apt");

        List<ClassName> mList = new ArrayList<>();
        CodeBlock.Builder blockBuilder = CodeBlock.builder();

        blockBuilder.beginControlFlow(" switch (mClass.getCanonicalName())");

        for (TypeElement element : ElementFilter.typesIn(roundEnv.getElementsAnnotatedWith(MvpEmptyViewFactory.class))) {
            ClassName currentType = ClassName.get(element);
            if (mList.contains(currentType)) {
                continue;
            }
            mList.add(currentType);
            //生成本身的方法
            StringBuilder s = new StringBuilder();
            List<? extends Element> enclosedElements = element.getEnclosedElements();
            for (int i = 0; i < enclosedElements.size(); i++) {
                if (enclosedElements.get(i) instanceof ExecutableElement) {
                    ExecutableElementBean elementBean = ExecutableElementParseUtil.parseElement((ExecutableElement) enclosedElements.get(i));
                    s.append("@Override ").append("public").append(" ").append(elementBean.returnType).append(" ").append(elementBean.methodName).append("(").append(elementBean.params).append(")").append(String.format("{%s}\n", ExecutableElementParseUtil.getReturnType(elementBean)));
                }
            }
            //生成父类的接口方法(这是一个递归的操作)
            getSuperFun(element, s);
            blockBuilder.addStatement("case $S : \n return  new $T(){ \n$L }", element.toString(), currentType, s);
        }

        blockBuilder.addStatement("default: return null");
        blockBuilder.endControlFlow();
        mMethod.addCode(blockBuilder.build());
        mClass.addMethod(mMethod.build());

        //设置apt生成文件的位置
        JavaFile javaFile = JavaFile.builder("com.dimples.mvp.apt", mClass.build()).build();
        try {
            javaFile.writeTo(processingEnv.getFiler());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 递归获取 父类的方法
     *
     * @param element TypeElement
     * @param s       StringBuilder
     */
    private void getSuperFun(TypeElement element, StringBuilder s) {
        List<? extends TypeMirror> interfaces = element.getInterfaces();
        if (interfaces != null && interfaces.size() > 0) {
            for (int i = 0; i < interfaces.size(); i++) {
                TypeMirror typeMirror = interfaces.get(i);
                if (typeMirror instanceof DeclaredType) {
                    Element element1 = ((DeclaredType) typeMirror).asElement();
                    List<? extends Element> innerElements = element1.getEnclosedElements();
                    for (int j = 0; j < innerElements.size(); j++) {
                        if (innerElements.get(i) instanceof ExecutableElement) {
                            ExecutableElementBean elementBean = ExecutableElementParseUtil.parseElement((ExecutableElement) innerElements.get(j));
                            s.append("@Override ").append("public").append(" ").append(elementBean.returnType).append(" ").append(elementBean.methodName).append("(").append(elementBean.params).append(")").append(String.format("{%s}\n", ExecutableElementParseUtil.getReturnType(elementBean)));
                        }
                    }
                    if (element1 instanceof TypeElement) {
                        getSuperFun((TypeElement) element1, s);
                    }
                }
            }
        }
    }

}
