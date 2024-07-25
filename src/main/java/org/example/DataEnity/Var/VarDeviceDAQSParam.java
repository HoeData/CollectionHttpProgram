package org.example.DataEnity.Var;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.DataEnity.Map.MapDPPoAndCommPlugin;

import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VarDeviceDAQSParam implements Serializable {
    private VarSCommPluginParam varSCommPluginParam;
    private VarSPPoParamEditOneEquip varSPPoParamEditOneEquip;
    private List<MapDPPoAndCommPlugin> mapDPPoAndCommPlugin;
    private VarTimeoutParam varTimeoutParam;
    private long varWH;
}
