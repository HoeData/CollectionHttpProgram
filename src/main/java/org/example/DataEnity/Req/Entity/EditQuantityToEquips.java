package org.example.DataEnity.Req.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.DataEnity.Seq.SeqDParam;
import org.example.DataEnity.Var.VarDAQSParam;
import org.example.DataEnity.Var.VarMiniInfoEditQuantityToEquip;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditQuantityToEquips implements Serializable {
    private VarMiniInfoEditQuantityToEquip varMiniInfo;
    private VarDAQSParam varDAQSParam;
    private SeqDParam seqDParam;
}
