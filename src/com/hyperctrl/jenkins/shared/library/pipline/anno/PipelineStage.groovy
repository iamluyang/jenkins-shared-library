package com.hyperctrl.jenkins.shared.library.pipline.anno

import com.hyperctrl.jenkins.shared.library.type.StageType

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface PipelineStage {

    /**
     * stage name
     * @return
     */
    String name() default "";

    /**
     * stage type
     * @return
     */
    StageType stage();

}