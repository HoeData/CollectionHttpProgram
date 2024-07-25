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
public class VarPluginParam implements Serializable {
    private List<SeqAllParam> seqAllParam;
    private String szLibFileName;
    private String szLibName;
    private long varPos;
    private long varType;
}
