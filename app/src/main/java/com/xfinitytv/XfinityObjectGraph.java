package com.xfinitytv;

import dagger.ObjectGraph;

public class XfinityObjectGraph {

    private XfinityObjectGraph() {

    }

    private static ObjectGraph objectGraph;

    public static void initialize(Object... modules) {
        objectGraph = ObjectGraph.create(modules);
    }

    public static ObjectGraph getObjectGraph() {
        return objectGraph;
    }

    public static void inject(Object object) {
        objectGraph.inject(object);
    }
}
