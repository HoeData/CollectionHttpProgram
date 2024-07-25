package org.example.DataEnity.Seq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeqCommParam implements Serializable {
    private String szPluginObjID;
    private List<SeqQParam> seqQParam;
}
