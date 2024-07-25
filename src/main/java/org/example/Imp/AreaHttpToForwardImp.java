package org.example.Imp;

import com.alibaba.fastjson.JSON;
import org.example.DataEnity.Req.*;
import org.example.DataEnity.Result.*;
import org.example.Interface.HttpToCollectAndForward;
import org.example.Utils.OkHttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AreaHttpToForwardImp implements HttpToCollectAndForward {
    private static final Logger log = LoggerFactory.getLogger(AreaHttpToForwardImp.class);
    private String ip = "127.0.0.1";
    private String port = "60000";
    private String address;

    public AreaHttpToForwardImp(String ip, String port) {
        this.ip = ip;
        this.port = port;
        this.address = "http://" + ip + ":" + port + "/api/v1/";
    }

    public Result<List<GetAllPlugObjResult>> GetAllPlugObj() {
        Result<List<GetAllPlugObjResult>> result = null;

        try {
            String json = OkHttpUtil.doPost(this.address + "GetAllPlugObj");
            result = (Result) JSON.parseObject(json, Result.class);
        } catch (Exception var3) {
            log.error(var3.toString());
        }

        return result;
    }

    public Result<List<CollectionInterfaceResult>> GetAllDaqStation() {
        Result<List<CollectionInterfaceResult>> result = null;

        try {
            String json = OkHttpUtil.doPost(this.address + "GetAllDaqStation");
            result = (Result)JSON.parseObject(json, Result.class);
        } catch (Exception var3) {
            log.error(var3.toString());
        }

        return result;
    }

    public Result<List<GetAllEquipByStationIDResult>> GetAllEquipByStationID(GetAllEquipByStationIDRequest getAllEquipByStationIDRequest) {
        assert !getAllEquipByStationIDRequest.IsEmptyHttpGetAllEquipByStationID() : "StationID exit empty or null";

        Result<List<GetAllEquipByStationIDResult>> result = null;
        Map<String, Object> params = new HashMap();
        params.put("StationID", getAllEquipByStationIDRequest.getStationID());

        try {
            String json = OkHttpUtil.doPost(this.address + "GetAllEquipByStationID", JSON.toJSONString(params));
            result = (Result)JSON.parseObject(json, Result.class);
        } catch (Exception var5) {
            log.error(var5.toString());
        }

        return result;
    }

    public Result<List<GetAllQuantityInEquipResult>> GetAllQuantityInEquip(GetAllQuantityInEquipRequest getAllQuantityInEquipRequest) {
        assert !getAllQuantityInEquipRequest.IsEmptyHttpGetAllQuantityInEquipRequest() : "StationID or equipId exit empty or null";

        Result<List<GetAllQuantityInEquipResult>> result = null;
        Map<String, Object> params = new HashMap();
        params.put("StationID", getAllQuantityInEquipRequest.getStationId());
        params.put("EquipID", getAllQuantityInEquipRequest.getEquipId());

        try {
            String json = OkHttpUtil.doPost(this.address + "GetAllQuantityInEquip",JSON.toJSONString( params));
            result = (Result)JSON.parseObject(json, Result.class);
        } catch (Exception var5) {
            log.error(var5.toString());
        }

        return result;
    }

    public Result<AddEquipToDaqStationResult> AddEquipToDaqStation(AddEquipToDaqStationRequest addEquipToDaqStationRequest) {
        assert !addEquipToDaqStationRequest.IsEmptyHttpAddEquipToDaqStationRequest() : "value exit empty or null";

        Result<AddEquipToDaqStationResult> result = null;
        Map<String, Object> params = new HashMap();
        params.put("varMiniInfo", addEquipToDaqStationRequest.getVarMiniInfo());
        params.put("varParam", addEquipToDaqStationRequest.getVarParam());

        try {
            String json = OkHttpUtil.doPost(this.address + "AddEquipToDaqStation",JSON.toJSONString( params));
            result = (Result)JSON.parseObject(json, Result.class);
        } catch (Exception var5) {
            log.error(var5.toString());
        }

        return result;
    }

    public Result<String> DelEquipFromDaqStation(DelEquipFromDaqStationRequest delEquipFromDaqStationRequest) {
        assert !delEquipFromDaqStationRequest.IsEmptyHttpDelEquipFromDaqStationRequest() : "value exit empty or null";

        Result<String> result = null;
        Map<String, Object> params = new HashMap();
        params.put("szDAQSUUID", delEquipFromDaqStationRequest.getSzDAQSUUID());
        params.put("szDAQEquipID", delEquipFromDaqStationRequest.getSzDAQEquipID());

        try {
            String json = OkHttpUtil.doPost(this.address + "DelEquipFromDaqStation",JSON.toJSONString( params));
            result = (Result)JSON.parseObject(json, Result.class);
        } catch (Exception var5) {
            log.error(var5.toString());
        }

        return result;
    }

    public Result<String> EditOneEquip(EditOneEquipRequest editOneEquipRequest) {
        assert !editOneEquipRequest.IsEmptyHttpGetAllEquipByStationID() : "value exit empty or null";

        Result<String> result = null;
        Map<String, Object> params = new HashMap();
        params.put("varMiniInfo", editOneEquipRequest.getVarMiniInfo());
        params.put("varParam", editOneEquipRequest.getVarDeviceDAQSParam());

        try {
            String json = OkHttpUtil.doPost(this.address + "EditOneEquip",JSON.toJSONString( params));
            result = (Result)JSON.parseObject(json, Result.class);
        } catch (Exception var5) {
            log.error(var5.toString());
        }

        return result;
    }

    public Result<List<AddQuantityToEquipResult>> AddQuantityToEquip(AddQuantityToEquipRequest addQuantityToEquipRequests) {
        assert !addQuantityToEquipRequests.IsEmptyHttpDelEquipFromDaqStationRequest() : "value exit empty or null";

        Result<List<AddQuantityToEquipResult>> result = null;

        try {
            String json = OkHttpUtil.doPost(this.address + "AddQuantityToEquip", JSON.toJSONString(addQuantityToEquipRequests.getAddQuantityToEquips()));
            result = (Result)JSON.parseObject(json, Result.class);
        } catch (Exception var4) {
            log.error(var4.toString());
        }

        return result;
    }

    public Result<String> EditQuantityToEquip(EditQuantityToEquipRequest editQuantityToEquipRequests) {
        assert !editQuantityToEquipRequests.IsEmptyHttpEditQuantityToEquipRequest() : "value exit empty or null";

        Result<String> result = null;

        try {
            String json = OkHttpUtil.doPost(this.address + "EditQuantityToEquip", JSON.toJSONString(editQuantityToEquipRequests));
            result = (Result)JSON.parseObject(json, Result.class);
        } catch (Exception var4) {
            log.error(var4.toString());
        }

        return result;
    }

    public Result<List<DeleteQuantityFromEquipResult>> DeleteQuantityFromEquip(DeleteQuantityFromEquipRequest deleteQuantityFromEquipRequests) {
        assert !deleteQuantityFromEquipRequests.IsEmptyHttpDeleteQuantityFromEquipRequest() : "value exit empty or null";

        Result<List<DeleteQuantityFromEquipResult>> result = null;

        try {
            String json = OkHttpUtil.doPost(this.address + "DeleteQuantityFromEquip", JSON.toJSONString(deleteQuantityFromEquipRequests));
            result = (Result)JSON.parseObject(json, Result.class);
        } catch (Exception var4) {
            log.error(var4.toString());
        }

        return result;
    }

    public Result<String> StartDaqInterface(StartDaqInterfaceRequest startDaqInterfaceRequest) {
        assert !startDaqInterfaceRequest.IsEmptyHttpStartDaqInterfaceRequest() : "value exit empty or null";

        Result<String> result = null;
        Map<String, Object> params = new HashMap();
        params.put("szDAQSUUID", startDaqInterfaceRequest.getSzDAQSUUID());

        try {
            String json = OkHttpUtil.doPost(this.address + "StartDaqInterface", JSON.toJSONString(params));
            result = (Result)JSON.parseObject(json, Result.class);
        } catch (Exception var5) {
            log.error(var5.toString());
        }

        return result;
    }

    public Result<String> StopDaqInterface(StopDaqInterfaceRequest stopDaqInterfaceRequest) {
        assert !stopDaqInterfaceRequest.IsEmptyHttpStopDaqInterfaceRequest() : "value exit empty or null";

        Result<String> result = null;
        Map<String, Object> params = new HashMap();
        params.put("szDAQSUUID", stopDaqInterfaceRequest.getSzDAQSUUID());

        try {
            String json = OkHttpUtil.doPost(this.address + "StopDaqInterface", JSON.toJSONString(params));
            result = (Result)JSON.parseObject(json, Result.class);
        } catch (Exception var5) {
            log.error(var5.toString());
        }

        return result;
    }

    public Result<AddDaqStationResult> AddDaqStation(AddDaqStationRequest addDaqStation) {
        assert !addDaqStation.IsEmptyHttpAddDaqStationRequest() : "value exit empty or null";

        Result<AddDaqStationResult> result = null;

        try {
            String json = OkHttpUtil.doPost(this.address + "AddDaqStation", JSON.toJSONString(addDaqStation));
            result = (Result)JSON.parseObject(json, Result.class);
        } catch (Exception var4) {
            log.error(var4.toString());
        }

        return result;
    }

    public Result<AddOnePlugObjResult> AddOnePlugObj(AddOnePlugObjRequest addOnePlugObjRequest) {
        assert !addOnePlugObjRequest.IsEmptyHttpAddOnePlugObjRequest() : "value exit empty or null";

        Result<AddOnePlugObjResult> result = null;

        try {
            String json = OkHttpUtil.doPost(this.address + "AddOnePlugObj", JSON.toJSONString(addOnePlugObjRequest));
            result = (Result)JSON.parseObject(json, Result.class);
        } catch (Exception var4) {
            log.error(var4.toString());
        }

        return result;
    }

    @Override
    public Result<String> DelDaqStation(DelDaqStationRequest delDaqStationRequest) {
        assert !delDaqStationRequest.IsEmptyHttpDelDaqStationRequest() : "value exit empty or null";
        Result<String> result = null;
        Map<String, Object> params = new HashMap();
        params.put("szDAQSUUID", delDaqStationRequest.getSzDAQSUUID());
        try {
            String json = OkHttpUtil.doPost(this.address + "DelDaqStation", JSON.toJSONString(params));
            result = (Result)JSON.parseObject(json, Result.class);
        } catch (Exception var5) {
            log.error(var5.toString());
        }

        return result;
    }
}
