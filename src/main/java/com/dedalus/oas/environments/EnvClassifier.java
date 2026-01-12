package com.dedalus.oas.environments;

public enum EnvClassifier {
    DEV("_dev_fra"), QA(""), REL("_rel");

    private final String suffix;

    EnvClassifier(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }
}
