package org.example.Interface;

import org.example.DataEnity.Req.*;
import org.example.DataEnity.Result.Result;

public interface HttpToCollectAndForward {
    Result GetAllPlugObj();

    Result GetAllDaqStation();

    Result GetAllEquipByStationID(GetAllEquipByStationIDRequest var1) throws RuntimeException;

    Result GetAllQuantityInEquip(GetAllQuantityInEquipRequest var1);

    Result AddEquipToDaqStation(AddEquipToDaqStationRequest var1);

    Result DelEquipFromDaqStation(DelEquipFromDaqStationRequest var1);

    Result EditOneEquip(EditOneEquipRequest var1);

    Result AddQuantityToEquip(AddQuantityToEquipRequest var1);

    Result EditQuantityToEquip(EditQuantityToEquipRequest var1);

    Result DeleteQuantityFromEquip(DeleteQuantityFromEquipRequest var1);

    Result StartDaqInterface(StartDaqInterfaceRequest var1);

    Result StopDaqInterface(StopDaqInterfaceRequest var1);

    Result AddDaqStation(AddDaqStationRequest var1);

    Result AddOnePlugObj(AddOnePlugObjRequest var1);

    Result DelDaqStation(DelDaqStationRequest var1);
}
