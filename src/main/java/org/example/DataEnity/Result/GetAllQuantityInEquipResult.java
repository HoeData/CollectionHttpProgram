package org.example.DataEnity.Result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.DataEnity.Seq.SeqDParam;
import org.example.DataEnity.Var.VarMiniInfoQuantityInEquip;
import org.example.DataEnity.Var.VarSParam;

import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllQuantityInEquipResult implements Serializable {
    private VarMiniInfoQuantityInEquip varMiniInfo;
    private VarSParam varDAQSParam;
    private List<SeqDParam> seqDParam;
}
