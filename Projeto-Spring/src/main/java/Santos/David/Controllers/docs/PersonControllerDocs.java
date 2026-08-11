package Santos.David.Controllers.docs;

import Santos.David.File.exporter.MidiaTypes;
import Santos.David.data.dto.PersonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PersonControllerDocs {
    @Operation(
            summary = "Find a Person",
            description = "Find a specific Person by your id",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content =
                            @Content(schema = @Schema(implementation = PersonDTO.class))
                    ),
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    PersonDTO findById(@PathVariable("id") Long id);


    @Operation(
            summary = "Find All People",
            description = "Find All People",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = PersonDTO.class)
                                            )
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    ResponseEntity<PagedModel<EntityModel<PersonDTO>>> findAll(
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "size",defaultValue = "12") Integer size,
            @RequestParam(value = "direction",defaultValue = "asc") String direction
    );

    @Operation(
            summary = "Export People",
            description = "Export a page of People in XLSX and csv format",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(mediaType = MidiaTypes.APPLICATION_XLSX_VALUE),
                                    @Content(mediaType = MidiaTypes.APPLICATION_CSV_VALUE)

                            }
                    ),
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )

    ResponseEntity<Resource> exportPage(
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "size",defaultValue = "12") Integer size,
            @RequestParam(value = "direction",defaultValue = "asc") String direction,
            HttpServletRequest request
    );

    @Operation(
            summary = "Massive People Creation",
            description = "Massive People Creation with upload of XLSX OR CSV.",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(schema = @Schema(implementation = PersonDTO.class))
                            }
                    ),
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    List<PersonDTO> massCreation(MultipartFile file);

    @Operation(
            summary = "Find People by FirstName",
            description = "Find People by their First Names",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = PersonDTO.class)
                                            )
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    ResponseEntity<PagedModel<EntityModel<PersonDTO>>> findByName(
            @PathVariable("firstName") String firstName,
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "size",defaultValue = "12") Integer size,
            @RequestParam(value = "direction",defaultValue = "asc") String direction
    );


    @Operation(
            summary = "Create a Person",
            description = "Create a person",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content =
                            @Content(schema = @Schema(implementation = PersonDTO.class))
                    ),
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    PersonDTO create(@RequestBody PersonDTO person);


    @Operation(
            summary = "Update a Person",
            description = "Update a specific Person",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content =
                            @Content(schema = @Schema(implementation = PersonDTO.class))
                    ),
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    PersonDTO update(@RequestBody PersonDTO person);

    @Operation(
            summary = "Dislable a Person",
            description = "Dislable a specific Person by your id",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content =
                            @Content(schema = @Schema(implementation = PersonDTO.class))
                    ),
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    PersonDTO disablePerson(@PathVariable("id") Long id);


    @Operation(
            summary = "Delete a Person",
            description = "Delete a specific Person by your id",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content =
                            @Content(schema = @Schema(implementation = PersonDTO.class))
                    ),
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    ResponseEntity<Void> delete(@PathVariable("id") Long id);
}
