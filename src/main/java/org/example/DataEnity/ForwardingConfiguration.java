package org.example.DataEnity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.DataEnity.Req.Entity.AddQuantityToEquips;
import org.example.DataEnity.Var.VarPluginParam;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForwardingConfiguration {
    private String forwarId;
    private List<String> collectIdRestart;
    private List<String> dataSourcePid;
    private String UniqueId;
    private String szStationName;
    private VarPluginParam varPluginParam;
    private String forwardIp;
    private String forwardPort;
    private List<AddQuantityToEquips> pointName;

}
