package org.example.DataEnity.Var;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.DataEnity.Map.MapDPPoAndCommPlugin;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VarCollectionDAQSParam {
    private long varWH;
    private MapDPPoAndCommPlugin mapDPPoAndCommPlugin;
    private VarSCommPluginParam varSCommPluginParam;
    private VarPluginParam varSPPoParam;
    private VarTimeoutParam varTimeoutParam;
}
