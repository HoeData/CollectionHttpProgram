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
public class VarParam implements Serializable {
    private VarAddEquipToDaqStationSPPoParam varSPPoParam;
    private List<MapDPPoAndCommPlugin> mapDPPoAndCommPlugin;
    private VarSCommPluginParam varSCommPluginParam;
}
