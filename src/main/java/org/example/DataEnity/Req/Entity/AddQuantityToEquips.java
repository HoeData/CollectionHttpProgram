package org.example.DataEnity.Req.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.DataEnity.Seq.SeqDParam;
import org.example.DataEnity.Var.VarMiniInfoAddQuantityToEquip;
import org.example.DataEnity.Var.VarSParam;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddQuantityToEquips implements Serializable {
    private VarMiniInfoAddQuantityToEquip varMiniInfo;
    private VarSParam varSParam;
    private List<SeqDParam> seqDParam;
}
