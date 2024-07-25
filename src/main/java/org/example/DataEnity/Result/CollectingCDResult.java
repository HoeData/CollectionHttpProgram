package org.example.DataEnity.Result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.DataEnity.Var.VarDAQSParam;
import org.example.DataEnity.Var.VarMiniInfo;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectingCDResult implements Serializable {
    private VarMiniInfo varMiniInfo;
    private VarDAQSParam varDAQSParam;
}
