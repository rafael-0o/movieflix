package br.com.movieflix.Service;

import br.com.movieflix.Entity.Streaming;
import br.com.movieflix.Repository.StreamingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {
    private final StreamingRepository streamingRepository;
    public List<Streaming> findAll(){
        return streamingRepository.findAll();
    }
    @Transactional
    public Streaming saveStreaming(Streaming streaming){
        return streamingRepository.save(streaming);
    }
    public Optional<Streaming> findById(Long id){
        return streamingRepository.findById(id);
    }
    public void delete(Long id){
        streamingRepository.deleteById(id);
    }


}
