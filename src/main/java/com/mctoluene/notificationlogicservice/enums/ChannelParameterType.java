package com.mctoluene.notificationlogicservice.enums;

import java.util.Optional;

public enum ChannelParameterType {

    PHONE("PHONE"), EMAIL("EMAIL");

    ChannelParameterType(String name) {
        this.name = name;
    }

    private String name;

    public static Optional<ChannelParameterType> getChannelParameterType(String name) {

        for (ChannelParameterType v : values())
            if (v.name.equalsIgnoreCase(name))
                return Optional.of(v);

        return Optional.empty();
    }
}