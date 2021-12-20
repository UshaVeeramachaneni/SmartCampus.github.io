package app.college.smartcampus.network;


import java.util.List;
import java.util.Map;

import app.college.smartcampus.models.Attendance;
import app.college.smartcampus.models.Mark;
import app.college.smartcampus.models.Notification;
import app.college.smartcampus.models.Placement;
import app.college.smartcampus.models.Response;
import app.college.smartcampus.models.StudyCorner;
import app.college.smartcampus.models.User;
import app.college.smartcampus.utils.Constants;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NetworkService {

    @FormUrlEncoded
    @POST(Constants.REGISTER)
    Call<Response> registerUser(@FieldMap Map<String, String> userMap);

    @GET(Constants.GET_PLACEMENTS)
    Call<Response<List<Placement>>> getPlacements();

    @GET(Constants.GET_GALLERY)
    Call<Response<List<String>>> getGallery();

    @FormUrlEncoded
    @POST(Constants.GET_NOTIFICATION)
    Call<Response<List<Notification>>> getNotifications(@Field("type") String type);

    @FormUrlEncoded
    @POST(Constants.PAY_FEE)
    Call<Response> payFee(@Field("rollno") String rollno, @Field("year") String year);

    @FormUrlEncoded
    @POST(Constants.GET_STUDY_CORNER)
    Call<Response<List<StudyCorner>>> getStudyMaterials(@Field("branch") String branch, @Field("year") String year, @Field("sem") String sem);

    @FormUrlEncoded
    @POST(Constants.GET_MARKS)
    Call<Response<List<Mark>>> getMarks(@Field("rollno") String rollno, @Field("year") String year, @Field("sem") String sem);

    @FormUrlEncoded
    @POST(Constants.GET_ATTENDANCE)
    Call<Response<List<Attendance>>> getAttendance(@Field("rollno") String rollno, @Field("year") String year, @Field("month") String month);

    @FormUrlEncoded
    @POST(Constants.LOGIN)
    Call<Response<User>> loginUser(@Field("rollno") String rollno, @Field("password") String password);
}
