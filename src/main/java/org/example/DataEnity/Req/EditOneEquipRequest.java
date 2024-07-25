package org.example.DataEnity.Req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.example.DataEnity.Var.VarDeviceDAQSParam;
import org.example.DataEnity.Var.VarMiniInfo;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditOneEquipRequest implements Serializable {
    private VarMiniInfo varMiniInfo;
    private VarDeviceDAQSParam varDeviceDAQSParam;

    public boolean IsEmptyHttpGetAllEquipByStationID() {
        return StringUtils.isEmpty(this.varMiniInfo.getSzDAQSUUID()) || StringUtils.isEmpty(this.varMiniInfo.getSzDAQEquipID()) || StringUtils.isEmpty(this.varMiniInfo.getSzDesc()) || StringUtils.isEmpty(this.varDeviceDAQSParam.getVarSPPoParamEditOneEquip().getSzLibFileName()) || this.varDeviceDAQSParam.getVarSPPoParamEditOneEquip().getSeqAllParam().stream().filter((res) -> {
            return StringUtils.isEmpty(res.getSzParamName()) || StringUtils.isEmpty(res.getSzParamValue());
        }).count() > 0L || this.varDeviceDAQSParam.getMapDPPoAndCommPlugin().stream().filter((res) -> {
            return StringUtils.isEmpty(res.getSzDPPOPluginObjID()) || res.getSzDPPOPluginObjID() != null && !res.getSzDPPOPluginObjID().isEmpty();
        }).count() > 0L || StringUtils.isEmpty(this.varDeviceDAQSParam.getVarSCommPluginParam().getSzLibFileName()) || this.varDeviceDAQSParam.getVarSCommPluginParam().getSeqAllParam().stream().filter((res) -> {
            return StringUtils.isEmpty(res.getSzParamName()) || StringUtils.isEmpty(res.getSzParamValue()) || StringUtils.isNotBlank(res.getSzParamDesc());
        }).count() > 0L;
    }
}
