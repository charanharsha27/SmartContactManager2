package com.scm.SmartContactManager.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class searchContactsForm {

    private String searchType;
    private String searchValue;

}
