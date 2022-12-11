package com.hyperctrl.jenkins.shared.library.utils

import com.google.common.reflect.ClassPath
import com.hyperctrl.jenkins.shared.library.pipline.anno.PipelineStage

import java.lang.annotation.Annotation
import java.util.stream.Collectors

@groovy.util.logging.Slf4j
class ScannerUtils {

    private ScannerUtils() {

    }

    /**
     * scan by packageName
     * @param packageName
     * @return
     */
    static Set<Class> scanBy(String packageName) {

        try {
            return ClassPath.from(ClassLoader.getSystemClassLoader())
                    .getTopLevelClassesRecursive(packageName)
                    .stream()
                    .collect(Collectors.toSet())
        } catch (Exception e) {
            log.error(e.getMessage(), e)
        }
    }

    /**
     * scan by class
     * @param packageName
     * @return
     */
    static <T> Set<Class<T>> scanByClass(String packageName, Class<T> type) {
        Set<Class> results = new HashSet<>()
        def classes = scanBy(packageName)
        for (Class clazz : classes) {
            if (type.isAssignableFrom(clazz) && type != clazz) {
                results.add(clazz)
            }
        }
        return results;
    }

    /**
     * scan by anno
     * @param packageName
     * @return
     */
    static Set<Class> scanByAnnotation(String packageName, Class<? extends Annotation> anno) {
        Set<Class> results = new HashSet<>()
        def classes = scanBy(packageName)
        for (Class clazz : classes) {
            if (clazz.getAnnotation(anno) != null) {
                results.add(clazz);
            }
        }
        return results;
    }

    static void main(String[] args) {
        Set<Class> classes = ScannerUtils.scanByAnno("com.hyperctrl.jenkins", PipelineStage.class)
        for (Class clazz : classes) {
            println clazz.name
        }
    }
}
