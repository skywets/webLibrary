package service;


import dao.ReviewDao;
import entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ReviewService {
    private ReviewDao reviewDao;

    public List<Review> getAll() {
        return reviewDao.getAll();
    }
    public Review getItem(long id) {
        return reviewDao.get(id).orElseThrow();
    }

    public void createReview(Review review) {
        reviewDao.create(review);
    }

    public void editReview(long id,Review review) {
        review.setId(id);
        reviewDao.update(review);
    }

    public void deleteReview(Review review) {
        reviewDao.delete(review);
    }
}
