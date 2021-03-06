//package repository;
//
//import entity.UserEntity;
//import enumtype.AppSourceEnum;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//
///**
// * @Description: 用户dao
// * @Author: zhaoyu.cui
// * @Date: 16/10/9
// * @Time: 下午5:06
// */
////@Repository
//public interface UserRepository {
//
//	void insert(UserEntity entity);
//
//	long nextSequence();
//
//	UserEntity findById(@Param("id") String id);
//
//	int update(UserEntity entity);
//
//	List<UserEntity> findByPhoneNo(@Param("phoneNo") String phoneNo);
//
//	UserEntity findByPhoneAndSource(@Param("phoneNo") String phoneNo,
//                                    @Param("appSource") AppSourceEnum appSourceEnum);
//
//	UserEntity findByMemberNo(@Param("memberNo") String memberNo);
//
//	List<UserEntity> findByMerchantNo(@Param("merchantNo") String merchantNo);
//
//	UserEntity findBossByMerchantNo(@Param("merchantNo") String merchantNo);
//}
