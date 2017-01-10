package com.noasking.datatables.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.noasking.datatables.mapping.DataTablesInput;
import com.noasking.datatables.mapping.DataTablesOutput;
import com.noasking.datatables.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaJing on 2016/12/29.
 */
@Controller
@RequestMapping("/data")
public class DataController {

    @JsonView(DataTablesOutput.View.class)
    @RequestMapping(value = "/data/users", method = RequestMethod.GET)
    public DataTablesOutput<User> getUsers(@Valid DataTablesInput input) {

        DataTablesOutput<User> output = new DataTablesOutput<User>();
        output.setDraw(input.getDraw());
        if (input.getLength() == 0) {
            return output;
        }

        List<User> users = new ArrayList<User>();
        User user = new User();
        users.add(user);
        users.add(user);
        users.add(user);
        users.add(user);
        users.add(user);

        output.setData(users);
        output.setRecordsTotal(users.size());

        return output;
    }

}
