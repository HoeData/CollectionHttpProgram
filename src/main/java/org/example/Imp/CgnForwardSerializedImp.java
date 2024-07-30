package org.example.Imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.example.DataEnity.ForwardingConfiguration;
import org.example.DataEnity.Map.MapDPPoAndCommPlugin;
import org.example.DataEnity.Req.*;
import org.example.DataEnity.Result.*;
import org.example.DataEnity.Seq.SeqAllParam;
import org.example.DataEnity.Var.*;
import org.example.Interface.ForwardSerialized;
import org.example.Utils.SerializationUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class CgnForwardSerializedImp implements ForwardSerialized {

    private AreaHttpToForwardImp areaHttpToForwardImp;

    public CgnForwardSerializedImp(AreaHttpToForwardImp areaHttpToForwardImp) {
        this.areaHttpToForwardImp = areaHttpToForwardImp;
    }

    public void ReadForwardConfigurationDatas(String fileName) {
        List<ForwardingConfiguration> forwardingConfigurations = JSON.parseArray((String) SerializationUtils.BinaryFileToUnSerialization(fileName), ForwardingConfiguration.class);
        Result<List<CollectionInterfaceResult>> rescission = this.areaHttpToForwardImp.GetAllDaqStation();
        List<CollectionInterfaceResult> data = JSON.parseArray(rescission.getData().toString(),CollectionInterfaceResult.class);

        CollectionInterfaceResult TwocollectionInterfaceResult = data.stream().filter(res -> res.getVarDAQStationMiniInfo().getSzStationName().contains("2区服务器性能")).findFirst().orElse(null);
        if (TwocollectionInterfaceResult!=null){
            String szDesc = TwocollectionInterfaceResult.getVarDAQStationMiniInfo().getSzDesc();
            forwardingConfigurations=forwardingConfigurations.stream().filter(res->res.getUniqueId().equals(szDesc)).collect(Collectors.toList());
        }
        CollectionInterfaceResult ThreecollectionInterfaceResult = data.stream().filter(res -> res.getVarDAQStationMiniInfo().getSzStationName().contains("3区服务器性能")).findFirst().orElse(null);
        if (ThreecollectionInterfaceResult!=null){
            String szDesc = ThreecollectionInterfaceResult.getVarDAQStationMiniInfo().getSzDesc();
            forwardingConfigurations=forwardingConfigurations.stream().filter(res->res.getUniqueId().equals(szDesc)).collect(Collectors.toList());
        }
        for(int i = 0; i < forwardingConfigurations.size(); ++i) {
            ForwardingConfiguration forward = forwardingConfigurations.get(i);
            forward.setCollectIdRestart(new ArrayList<>());
            new Thread(() -> {
                this.UpdateCollectionForwardingConfiguration(forward);
                this.AddForwardingInterfaceEquip(forward);
                forward.getCollectIdRestart().forEach((res) -> {
                    this.areaHttpToForwardImp.StartDaqInterface(new StartDaqInterfaceRequest(res));
                });
            }).start();
        }
        //查看是否有多余的接口需要删除
        List<String> forwardIds = forwardingConfigurations.stream().map(res -> res.getForwarId()+"-cgn自动创建转发接口").collect(Collectors.toList());
        Result<List<CollectionInterfaceResult>> result = this.areaHttpToForwardImp.GetAllDaqStation();
        List<CollectionInterfaceResult> datas = JSON.parseArray(result.getData().toString(),CollectionInterfaceResult.class);
        datas.stream().filter(res -> res.getVarDAQStationMiniInfo().getSzStationName().contains("cgn自动创建转发接口")
                && !forwardIds.contains(res.getVarDAQStationMiniInfo().getSzStationName())).forEach(res->{
                    //停止
            this.areaHttpToForwardImp.StopDaqInterface(new StopDaqInterfaceRequest(res.getVarDAQStationMiniInfo().getSzUUID()));
            //删除
            this.areaHttpToForwardImp.DelDaqStation(new DelDaqStationRequest(res.getVarDAQStationMiniInfo().getSzUUID()));
        });
    }

    /**
     * //RedisRead
     * {
     *     "szPluginObjID": "",
     *     "szPluginObjName": "RedisReadPlugin-1", //自定义
     *     "varPluginParam": {
     *         "seqAllParam": [
     *             {
     *                 "szParamName": "RedisIP",
     *                 "szParamValue": "127.0.0.1"
     *             },
     *             {
     *                 "szParamName": "RedisPort",
     *                 "szParamValue": "6379"
     *             },
     *             {
     *                 "szParamName": "ReadInterval",
     *                 "szParamValue": "1000"
     *             }
     *         ],
     *         "szLibFileName": "RedisReadPlugin"
     *     }
     * }
     * //RedisWrite
     * {
     *     "szPluginObjID": "",
     *     "szPluginObjName": "RedisWriteComm-1", //自定义
     *     "varPluginParam": {
     *         "seqAllParam": [
     *             {
     *                 "szParamName": "RedisIP",
     *                 "szParamValue": "127.0.0.1"
     *             },
     *             {
     *                 "szParamName": "RedisPort",
     *                 "szParamValue": "6379"
     *             }
     *         ],
     *         "szLibFileName": "RedisWrite"
     *     }
     * }
     * @param forwardingConfiguration
     */
    public void UpdateCollectionForwardingConfiguration(ForwardingConfiguration forwardingConfiguration) {
        Result<List<GetAllPlugObjResult>> getAllPlugObj = this.areaHttpToForwardImp.GetAllPlugObj();
        List<GetAllPlugObjResult> getAllPlugObjResultList = JSON.parseArray(((JSONArray) getAllPlugObj.getData()).toJSONString(),GetAllPlugObjResult.class);
        GetAllPlugObjResult getAllPlugObjResult = getAllPlugObjResultList.stream().filter(res ->res.getVarPluginParam().stream().filter(data->data.getSzLibFileName().equals("RedisWriteComm")).collect(Collectors.toList()).size()>0).findFirst().orElse(null);
        String redisId = "";
        if (getAllPlugObjResult == null) {
            VarPluginParam varPluginParam = new VarPluginParam();
            List<SeqAllParam> seqAllParams=new ArrayList<>();
            SeqAllParam RedisIP=new SeqAllParam();
            RedisIP.setSzParamName("RedisIP");
            RedisIP.setSzParamValue("127.0.0.1");
            seqAllParams.add(RedisIP);
            SeqAllParam RedisPort=new SeqAllParam();
            RedisPort.setSzParamName("RedisPort");
            RedisPort.setSzParamValue("6379");
            seqAllParams.add(RedisPort);
            varPluginParam.setSeqAllParam(seqAllParams);
            varPluginParam.setSzLibFileName("RedisWriteComm");
            Result<AddOnePlugObjResult> ResultRedis = this.areaHttpToForwardImp.AddOnePlugObj(new AddOnePlugObjRequest("", "RedisWriteComm-1",varPluginParam));
            JSON.parseArray(ResultRedis.getData().toString(),AddOnePlugObjResult.class);
            redisId = ((AddOnePlugObjResult)ResultRedis.getData()).getSzPluginObjID();
        }else{
             redisId = getAllPlugObjResult.getSzPluginObjID();
        }

        GetAllPlugObjResult redisCommunication = (GetAllPlugObjResult)getAllPlugObjResultList.stream().filter((res) -> {
            return res.getVarPluginParam().stream().filter(data->data.getSzLibFileName().equals("TransPPo")).collect(Collectors.toList()).size()>0;
        }).findAny().orElse(null);
        String redisCommunicationId="";
        if (redisCommunication == null) {
            Result rescission = this.areaHttpToForwardImp.AddOnePlugObj(new AddOnePlugObjRequest("", "TransPPo", (VarPluginParam)null));
            redisCommunicationId = ((AddOnePlugObjResult)rescission.getData()).getSzPluginObjID();
        }else{
            redisCommunicationId=redisCommunication.getSzPluginObjID();
        }

        Result<List<CollectionInterfaceResult>> rescission = this.areaHttpToForwardImp.GetAllDaqStation();
        List<CollectionInterfaceResult> data = JSON.parseArray(rescission.getData().toString(),CollectionInterfaceResult.class);
        List<String> dataSourcePid = forwardingConfiguration.getDataSourcePid();
        for(int i = 0; i < dataSourcePid.size(); ++i) {
            String pid = (String)dataSourcePid.get(i);
            List<CollectionInterfaceResult> collectionInterfaceResult = (List)data.stream().filter((res) -> {
                return res.getVarDAQStationMiniInfo().getSzDesc().contains(pid);
            }).collect(Collectors.toList());
            if (collectionInterfaceResult.size() > 0) {
                String finalRedisId = redisId;
                String finalRedisCommunicationId = redisCommunicationId;
                collectionInterfaceResult.forEach((datas) -> {
                    VarDAQStationMiniInfo varDAQStationMiniInfo = datas.getVarDAQStationMiniInfo();
                    String szUUID = varDAQStationMiniInfo.getSzUUID();
                    this.areaHttpToForwardImp.StopDaqInterface(new StopDaqInterfaceRequest(szUUID));
                    forwardingConfiguration.getCollectIdRestart().add(szUUID);
                    Result<List<GetAllEquipByStationIDResult>> getAllEquipByStationID = this.areaHttpToForwardImp.GetAllEquipByStationID(new GetAllEquipByStationIDRequest(szUUID));
                    List<GetAllEquipByStationIDResult> getAllEquipByStationIDResults = JSON.parseArray(getAllEquipByStationID.getData().toString(), GetAllEquipByStationIDResult.class);
                    getAllEquipByStationIDResults.forEach((res) -> {
                        VarMiniInfo varMiniInfo = res.getVarMiniInfo();
                        VarDeviceDAQSParam varDAQSParam = res.getVarDAQSParam();
                        List<MapDPPoAndCommPlugin> mapDPPoAndCommPlugin = varDAQSParam.getMapDPPoAndCommPlugin();
                        MapDPPoAndCommPlugin mapDPPoAndCommPlugin1 = mapDPPoAndCommPlugin.stream().filter(v -> v.getSzDPPOPluginObjID().equals(finalRedisCommunicationId)).findFirst().orElse(null);
                        if (mapDPPoAndCommPlugin1==null){
                            mapDPPoAndCommPlugin.add(new MapDPPoAndCommPlugin(finalRedisCommunicationId, Arrays.asList(finalRedisId)));
                        }else{
                            if (!mapDPPoAndCommPlugin1.getSeqDCommPluginObjID().contains(finalRedisId)){
                                mapDPPoAndCommPlugin1.getSeqDCommPluginObjID().add(finalRedisId);
                            }
                        }
                        this.areaHttpToForwardImp.EditOneEquip(new EditOneEquipRequest(varMiniInfo, varDAQSParam));
                    });
                });
            }
        }

    }

    public String AddForwardingInterfaceEquip(ForwardingConfiguration forwardingConfiguration) {
        Result<List<CollectionInterfaceResult>> rescission = this.areaHttpToForwardImp.GetAllDaqStation();
        List<CollectionInterfaceResult> data = JSON.parseArray(rescission.getData().toString(), CollectionInterfaceResult.class);
//        String name = forwardingConfiguration.getSzStationName() + "转发接口" + forwardingConfiguration.getDataSourcePid() + "-" + forwardingConfiguration.getForwardIp() + ":" + forwardingConfiguration.getForwardPort();
        String name =forwardingConfiguration.getForwarId() + "-cgn自动创建转发接口";
        CollectionInterfaceResult collectionInterfaceResult = (CollectionInterfaceResult)data.stream().filter((res) -> {
            return res.getVarDAQStationMiniInfo().getSzDesc()!=null&& res.getVarDAQStationMiniInfo().getSzDesc().equals(name);
        }).findAny().orElse(null);
        VarDAQStationMiniInfo varDAQStationMiniInfo;

        if (collectionInterfaceResult == null) {
            varDAQStationMiniInfo = new VarDAQStationMiniInfo(true, true, 1, 0, name, name, "", 1);
            VarDAQSParam varDAQSParam = new VarDAQSParam();
            Result<AddDaqStationResult> addDaqStationResultResult = this.areaHttpToForwardImp.AddDaqStation(new AddDaqStationRequest(varDAQStationMiniInfo, varDAQSParam));
            AddDaqStationResult addDaqStationResult = JSON.parseObject(JSON.toJSONString(addDaqStationResultResult.getData()), AddDaqStationResult.class);
            String stationID = addDaqStationResult.getStationID();
            this.AddEquipByStationID(stationID, name, forwardingConfiguration);
        } else {
            varDAQStationMiniInfo = collectionInterfaceResult.getVarDAQStationMiniInfo();
            String szStationId = varDAQStationMiniInfo.getSzUUID();
            Result<List<GetAllEquipByStationIDResult>> listResult = this.areaHttpToForwardImp.GetAllEquipByStationID(new GetAllEquipByStationIDRequest(szStationId));
            List<GetAllEquipByStationIDResult> getAllEquipByStationIDResults = JSON.parseArray(listResult.getData().toString(), GetAllEquipByStationIDResult.class);
            GetAllEquipByStationIDResult getAllEquipByStationIDResult = (GetAllEquipByStationIDResult)getAllEquipByStationIDResults.stream().filter((res) -> {
                return res.getVarMiniInfo().getSzDesc().equals(name);
            }).findFirst().orElse(null);
            if (getAllEquipByStationIDResult == null) {
                this.AddEquipByStationID(szStationId, name, forwardingConfiguration);
            }
        }

        return null;
    }

    public void AddEquipByStationID(String stationID, String szDAQEName, ForwardingConfiguration forwardingConfiguration) {
        this.areaHttpToForwardImp.StopDaqInterface(new StopDaqInterfaceRequest(stationID));
        forwardingConfiguration.getCollectIdRestart().add(stationID);
        Result<List<GetAllEquipByStationIDResult>> getAllEquipByStationID = this.areaHttpToForwardImp.GetAllEquipByStationID(new GetAllEquipByStationIDRequest(stationID));
        List<GetAllEquipByStationIDResult> allEquipByStationIDData = JSON.parseArray(getAllEquipByStationID.getData().toString(), GetAllEquipByStationIDResult.class);
        List<GetAllEquipByStationIDResult> coarsen = allEquipByStationIDData.stream().filter((res) -> {
            return res.getVarMiniInfo().getSzDAQEName().equals(szDAQEName);
        }).collect(Collectors.toList());
        Result<List<GetAllPlugObjResult>> getAllPlugObj = this.areaHttpToForwardImp.GetAllPlugObj();
        List<GetAllPlugObjResult> data = JSON.parseArray(((JSONArray) getAllPlugObj.getData()).toJSONString(),GetAllPlugObjResult.class);
        if (coarsen.size()>0) {
            for (int i = 0; i < coarsen.size(); i++) {
                GetAllEquipByStationIDResult getAllEquipByStationIDResult = coarsen.get(i);
                String szDAQEquipID = getAllEquipByStationIDResult.getVarMiniInfo().getSzDAQEquipID();
                Result<String> stringResult = this.areaHttpToForwardImp.DelEquipFromDaqStation(new DelEquipFromDaqStationRequest(stationID, szDAQEquipID));
            }
        }
        VarMiniInfoAddEquipToDaqStation varMiniInfoAddEquipToDaqStation = new VarMiniInfoAddEquipToDaqStation();
        varMiniInfoAddEquipToDaqStation.setSzDAQEName(szDAQEName);
        varMiniInfoAddEquipToDaqStation.setSzDAQSUUID(stationID);
        VarParam varParam = new VarParam();
        List<SeqAllParam> seqAllParamList=new ArrayList<>();
        SeqAllParam RedisIP=new SeqAllParam();
        RedisIP.setSzParamValue("127.0.0.1");
        RedisIP.setSzParamName("RedisIP");
        seqAllParamList.add(RedisIP);
        SeqAllParam RedisPort=new SeqAllParam();
        RedisPort.setSzParamValue("6379");
        RedisPort.setSzParamName("RedisPort");
        seqAllParamList.add(RedisPort);
        SeqAllParam ReadInterval=new SeqAllParam();
        ReadInterval.setSzParamValue("1000");
        ReadInterval.setSzParamName("ReadInterval");
        seqAllParamList.add(ReadInterval);
        varParam.setVarSPPoParam(new VarAddEquipToDaqStationSPPoParam("RedisReadPlugin","Redis(读取)", seqAllParamList));
        varParam.setVarSCommPluginParam(new VarSCommPluginParam("TransComm","TransComm(透传通信)",new ArrayList<>()));
        String szPluginObjID = "";
        GetAllPlugObjResult getAllPlugObjResult = (GetAllPlugObjResult)data.stream().filter((res) -> {
            return res.getSzPluginObjName().equals(forwardingConfiguration.getSzStationName());
        }).findFirst().orElse(null);
        if (getAllPlugObjResult == null) {
            Result<AddOnePlugObjResult> addOnePlugObjResultResult = this.areaHttpToForwardImp.AddOnePlugObj(new AddOnePlugObjRequest("", forwardingConfiguration.getSzStationName(), forwardingConfiguration.getVarPluginParam()));
            AddOnePlugObjResult addOnePlugObjResults = JSON.parseObject(addOnePlugObjResultResult.getData().toString(), AddOnePlugObjResult.class);
            szPluginObjID = addOnePlugObjResults.getSzPluginObjID();
        }
        else {
            szPluginObjID = getAllPlugObjResult.getSzPluginObjID();
        }
        String seqDCommPluginObjID = "";
        GetAllPlugObjResult getAllPlugObjseqDCommPluginObjIDResult = (GetAllPlugObjResult)data.stream().filter((res) -> {
            return res.getSzPluginObjName().equals("TCPServComm-1");
        }).findFirst().orElse(null);


        if (getAllPlugObjseqDCommPluginObjIDResult == null) {
            //参数RedisClientComm
            VarPluginParam varPluginParam = new VarPluginParam();
            varPluginParam.setSzLibFileName("TCPServComm");
            List<SeqAllParam> seqAllParams=new ArrayList<>();
            //TCPServBindPORT
            SeqAllParam TCPServBindPORT=new SeqAllParam();
            TCPServBindPORT.setSzParamValue("8001");
            TCPServBindPORT.setSzParamName("TCPServBindPORT");
            seqAllParams.add(TCPServBindPORT);
            //TCPServBindAddr
            SeqAllParam TCPServBindAddr=new SeqAllParam();
            TCPServBindAddr.setSzParamValue("0.0.0.0");
            TCPServBindAddr.setSzParamName("TCPServBindAddr");
            seqAllParams.add(TCPServBindAddr);
            varPluginParam.setSeqAllParam(seqAllParams);
            Result listResult = this.areaHttpToForwardImp.AddOnePlugObj(new AddOnePlugObjRequest("", "TCPServComm-1",varPluginParam));
            AddOnePlugObjResult addOnePlugObjResult = JSON.parseObject(listResult.getData().toString(), AddOnePlugObjResult.class);
            seqDCommPluginObjID = addOnePlugObjResult.getSzPluginObjID();
        }
        else {
            seqDCommPluginObjID = getAllPlugObjseqDCommPluginObjIDResult.getSzPluginObjID();
        }
        varParam.setMapDPPoAndCommPlugin(Arrays.asList(new MapDPPoAndCommPlugin(szPluginObjID, Arrays.asList(seqDCommPluginObjID))));
        Result<AddEquipToDaqStationResult> addEquipToDaqStationResultResult = this.areaHttpToForwardImp.AddEquipToDaqStation(new AddEquipToDaqStationRequest(varMiniInfoAddEquipToDaqStation, varParam));
        String equipID = JSON.parseObject(JSON.toJSONString(addEquipToDaqStationResultResult.getData()),AddEquipToDaqStationResult.class).getEquipID();
        String finalSzPluginObjID = szPluginObjID;
        forwardingConfiguration.getPointName().forEach((res) -> {
            res.getSeqDParam().stream().forEach(v->{
                v.setSzPPoPluginObjID(finalSzPluginObjID);
            });
            res.getVarMiniInfo().setSzDAQSUUID(stationID);
            res.getVarMiniInfo().setSzDAQEID(equipID);
        });
        this.areaHttpToForwardImp.AddQuantityToEquip(new AddQuantityToEquipRequest(forwardingConfiguration.getPointName()));
    }

    @Override
    public void WriteForwardConfigurationDatas(String path, String fileName, Object forwardingConfiguration) {
        File file = new File(path);
        // 检查文件是否存在
        if (!file.exists()) {
            boolean isFolderCreated = file.mkdirs();
        }
        File factual = new File(path + File.separator + fileName);
        // 检查文件是否存在，如果不存在则创建
        if (!factual.exists()) {
            try {
                boolean isFileCreated = file.createNewFile();
                if (isFileCreated) {
                    System.out.println("文件创建成功：" + factual);
                } else {
                    System.out.println("文件创建失败，可能的原因包括文件已存在或者没有写权限等。");
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("文件创建过程中发生错误：" + e.getMessage());
            }
        } else {
            System.out.println("文件已存在：" + factual);
        }
        SerializationUtils.SerializationToBinaryFile(path + File.separator + fileName, JSON.toJSONString(forwardingConfiguration));
    }
}
