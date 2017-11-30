import java.util.Date;
data class  TreatmentRecord (
	val id: Long,
	val treatmentRecordId: String,
	val medicalServiceItemId: String,
	val appointmentId: String,
	val productId: String,
	val productPackageId: String,
	val clinicId: String,
	val shopId: String,
	val patientId: String,
	val title: String,
	val productPackageTitle: String,
	val doctorId: String,
	val outpatientRecordCreateTime: Date,
	val serviceTimes: Integer,
	val executedTimes: Integer,
	val therapistUserId: String,
	val appointmentTime: Date,
	val isExecuted: Integer,
	val isAppointmented: Integer,
	val isAvailable: Integer,
	val createTime: Date,
	val updateTime: Date,
	val isPackage: Integer,
	val status: Integer
)

