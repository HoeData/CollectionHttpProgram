package org.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.example.DataEnity.ForwardingConfiguration;
import org.example.Imp.AreaHttpToForwardImp;
import org.example.Imp.CgnForwardSerializedImp;
import org.example.Utils.SerializationUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    private final static AreaHttpToForwardImp areaHttpToForwardImp = new AreaHttpToForwardImp("192.168.0.86", "8000");
    private final static CgnForwardSerializedImp cgnForwardSerializedImp = new CgnForwardSerializedImp(areaHttpToForwardImp);

    public static void main(String[] args) {
        cgnForwardSerializedImp.ReadForwardConfigurationDatas("forward.bin");
    }
}
