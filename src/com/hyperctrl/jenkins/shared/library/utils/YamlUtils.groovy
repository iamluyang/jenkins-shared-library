package com.hyperctrl.jenkins.shared.library.utils

import com.hyperctrl.jenkins.shared.library.bean.yaml.Apps
import com.hyperctrl.jenkins.shared.library.bean.yaml.YamlFile
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.representer.Representer

class YamlUtils {

    private YamlUtils() {
    }

    static Map<String, Object> load( script) {
        def workspace = script.WORKSPACE
        File file = new File(workspace, "pipeline.yaml")

        script.echo "#=================================================="
        script.echo "${file.text}"
        script.echo "#=================================================="

        Yaml yaml = new Yaml()
        Map<String, Object> obj = yaml.load(file.text)
        return obj
    }

    static YamlFile load() {
        Representer representer = new Representer();
        representer.getPropertyUtils().setSkipMissingProperties(true);
        Yaml yaml = new Yaml(representer);

        File file = new File( "pipeline.yaml")
        YamlFile obj = yaml.loadAs(file.text, YamlFile.class)
        return obj
    }

    static void main(String[] args) {
        YamlFile yamlFile = YamlUtils.load()
        List<Apps> apps = yamlFile.jenkins.apps
        for (Apps item : apps) {
            println(item)
        }
    }
}