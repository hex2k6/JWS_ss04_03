package re.ss04_03.Controller;

import org.springframework.web.bind.annotation.*;
import re.ss04_03.Model.Movie;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @GetMapping("/{movieId}")
    public Movie getMovieById(@PathVariable String movieId) {
        return new Movie(movieId, "Interstellar", "Sci-Fi");
    }

    @GetMapping
    public List<Movie> getMoviesByGenre(
            @RequestParam(required = false) String genre) {
        List<Movie> movies = List.of(
                new Movie("M001", "Interstellar", "Sci-Fi"),
                new Movie("M002", "Inception", "Sci-Fi"),
                new Movie("M003", "Titanic", "Romance")
        );
        if (genre == null) {
            return movies;
        }
        return movies.stream()
                .filter(m -> m.getGenre().equalsIgnoreCase(genre))
                .toList();
    }
}

//**So sánh:**
//        * `@PathVariable`
//        * Nằm trong URL path (ví dụ: `/movies/M001`)
//  * Dùng để **xác định một tài nguyên cụ thể**
//        * Thường là bắt buộc
//* `@RequestParam`
//        * Nằm trong query string (ví dụ: `/movies?genre=Sci-Fi`)
//  * Dùng để **lọc, tìm kiếm, phân trang**
//        * Có thể có hoặc không
//        **Áp dụng vào bài:**
//        * **Tình huống A (xem chi tiết phim)** → dùng `@PathVariable`
//Vì `movieId` là định danh duy nhất của một bộ phim.
//* **Tình huống B (lọc theo thể loại)** → dùng `@RequestParam`
//Vì `genre` chỉ là điều kiện lọc, không định danh tài nguyên.
//        **Vì sao không hoán đổi:**
//        * Dùng `@RequestParam` cho ID làm URL kém RESTful (ví dụ: `/movies?id=M001`)
//* Dùng `@PathVariable` cho filter làm URL khó mở rộng khi có nhiều điều kiện
//        **Nguyên tắc RESTful:**
//        * Path (`/resource/{id}`) → định danh tài nguyên
//* Query (`?key=value`) → lọc / tìm kiếm
