package br.com.movieflix.Controller;

import br.com.movieflix.Controller.Request.StreamingRequest;
import br.com.movieflix.Controller.Response.StreamingResponse;
import br.com.movieflix.Entity.Streaming;
import br.com.movieflix.Mapper.StreamingMapper;
import br.com.movieflix.Service.StreamingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movieflix/streaming")
@Tag(name="Streaming", description = "resource responsible for managing streamings")
public class StreamingController {
    private final StreamingService streamingService;

    @Operation(summary = "get all", description = "get all stremings saved",
    security=@SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "caught all streamings",
            content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    @GetMapping("/get")
    public ResponseEntity<List<StreamingResponse>> getAllStreamings(){
        List<StreamingResponse> streamingResponses = streamingService.findAll()
                .stream()
                .map(streaming -> StreamingMapper.toStreamingResponse(streaming))
                .toList();
        return ResponseEntity.ok(streamingResponses);
    }
    @Operation(summary = "create streaming", description = "create a new streaming",
    security=@SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "streaming created",
            content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    @PostMapping("/create")
    public ResponseEntity<StreamingResponse> save(@Valid @RequestBody StreamingRequest streamingRequest){
        Streaming newStreaming = StreamingMapper.toStreaming(streamingRequest);
        Streaming streaming = streamingService.saveStreaming(newStreaming);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StreamingMapper.toStreamingResponse(streaming));
    }
    @Operation(summary = "get one", description = "get streaming by id",
    security=@SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "found the streaming",
            content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    @GetMapping("/find/{id}")
    public ResponseEntity<StreamingResponse> findById(@PathVariable Long id){
        return streamingService.findById(id)
                .map(streaming ->ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "get all", description = "get all stremings saved",
    security=@SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "streaming deleted")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        streamingService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
