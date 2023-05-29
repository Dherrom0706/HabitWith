package www.iesmurgi.habitwith.models

import android.os.Parcel
import android.os.Parcelable

/***
 * Data class utilizada para la recogida de caracteristicas de la persona
 */
data class Datos(

    var edad: String? = "",
    var altura: String? = "",
    var peso: String? = "",
    var sexo: String? = "",
    var objetivo: String? = "",
    var id: String? = ""

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(edad)
        parcel.writeString(altura)
        parcel.writeString(peso)
        parcel.writeString(sexo)
        parcel.writeString(objetivo)
        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Datos> {
        override fun createFromParcel(parcel: Parcel): Datos {
            return Datos(parcel)
        }

        override fun newArray(size: Int): Array<Datos?> {
            return arrayOfNulls(size)
        }
    }
}
