package br.com.movieflix.Controller;

import br.com.movieflix.Controller.Request.StreamingRequest;
import br.com.movieflix.Controller.Response.StreamingResponse;
import br.com.movieflix.Entity.Streaming;
import br.com.movieflix.Mapper.StreamingMapper;
import br.com.movieflix.Service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movieflix/streaming")
public class StreamingController {
    private final StreamingService streamingService;
    @GetMapping("/get")
    public ResponseEntity<List<StreamingResponse>> getAllStreamings(){
        List<StreamingResponse> streamingResponses = streamingService.findAll()
                .stream()
                .map(streaming -> StreamingMapper.toStreamingResponse(streaming))
                .toList();
        return ResponseEntity.ok(streamingResponses);
    }
    @PostMapping("/create")
    public ResponseEntity<StreamingResponse> save(@RequestBody StreamingRequest streamingRequest){
        Streaming newStreaming = StreamingMapper.toStreaming(streamingRequest);
        Streaming streaming = streamingService.saveStreaming(newStreaming);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StreamingMapper.toStreamingResponse(streaming));
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<StreamingResponse> findById(@PathVariable Long id){
        return streamingService.findById(id)
                .map(streaming ->ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        streamingService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
