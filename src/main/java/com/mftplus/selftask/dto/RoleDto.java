package com.mftplus.selftask.dto;



import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
public class RoleDto extends JsonDeserializer<RoleDto>{

    private String roleName;  // We just need the role name

    public RoleDto(String roleName) {
        this.roleName = roleName;
    }

    public RoleDto() {
    }

    public String getRoleName() {
        return roleName;
    }

    public RoleDto setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }
    @Override
    public RoleDto deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        String roleName = node.asText();  // Deserialize the roleName from the JSON value

        return new RoleDto(roleName);
    }
}
