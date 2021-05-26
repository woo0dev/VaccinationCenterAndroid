package com.inhatc.vaccinationcenter;

import java.io.IOException;
import com.fasterxml.jackson.annotation.*;

public enum CenterType {
    CENTER_TYPE, EMPTY;

    @JsonValue
    public String toValue() {
        switch (this) {
            case CENTER_TYPE: return "\uc9c0\uc5ed";
            case EMPTY: return "\uc911\uc559/\uad8c\uc5ed";
        }
        return null;
    }

    @JsonCreator
    public static CenterType forValue(String value) throws IOException {
        if (value.equals("\uc9c0\uc5ed")) return CENTER_TYPE;
        if (value.equals("\uc911\uc559/\uad8c\uc5ed")) return EMPTY;
        throw new IOException("Cannot deserialize CenterType");
    }
}
