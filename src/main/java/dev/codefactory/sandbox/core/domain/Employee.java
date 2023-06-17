package dev.codefactory.sandbox.core.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Employee {

    String id;
    String name;
}
