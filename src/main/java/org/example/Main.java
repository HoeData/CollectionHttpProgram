package org.example;

import com.alibaba.fastjson.JSON;
import org.example.DataEnity.ForwardingConfiguration;
import org.example.Imp.AreaHttpToForwardImp;
import org.example.Imp.CgnForwardSerializedImp;
import org.example.Utils.SerializationUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private final static AreaHttpToForwardImp areaHttpToForwardImp = new AreaHttpToForwardImp("192.168.0.208", "60000");
    private final static CgnForwardSerializedImp cgnForwardSerializedImp = new CgnForwardSerializedImp(areaHttpToForwardImp);

    public static void main(String[] args) {
        cgnForwardSerializedImp.ReadForwardConfigurationDatas("forward.bin");
    }
}
