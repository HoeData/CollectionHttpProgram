package org.example.DataEnity.Result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.DataEnity.Var.VarCollectionDAQSParam;
import org.example.DataEnity.Var.VarDAQStationMiniInfo;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionInterfaceResult implements Serializable {
    private VarCollectionDAQSParam varDAQSParam;
    private VarDAQStationMiniInfo varDAQStationMiniInfo;
}
