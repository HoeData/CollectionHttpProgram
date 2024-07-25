package org.example.DataEnity.Var;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.DataEnity.Seq.SeqAllParam;

import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VarAddEquipToDaqStationSPPoParam implements Serializable {
    private String szLibFileName;
    private List<SeqAllParam> seqAllParam;

}
