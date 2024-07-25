package org.example.Interface;

import org.example.DataEnity.ForwardingConfiguration;

import java.util.HashMap;
import java.util.List;

public interface ForwardSerialized {
    void ReadForwardConfigurationDatas(String var1);

    void WriteForwardConfigurationDatas(String path, String fileName, Object forwardingConfiguration);
}
