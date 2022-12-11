package com.hyperctrl.jenkins.shared.library.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder

class JsonUtils {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private JsonUtils() {

    }

    static toJson(Object obj) {
        String json = gson.toJson(obj)
        return json
    }
}
