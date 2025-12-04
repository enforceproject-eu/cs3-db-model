package org.n52.project.enforce.cs3.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.n52.project.enforce.cs3.model.MultipointGeoJSON;
import org.n52.project.enforce.cs3.model.MultipolygonGeoJSON;
import org.n52.project.enforce.cs3.model.PointGeoJSON;
import org.springframework.lang.Nullable;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-04T12:24:27.832528100+01:00[Europe/Berlin]", comments = "Generator version: 7.13.0")
public interface GeometryGeoJSON extends Serializable {
}
