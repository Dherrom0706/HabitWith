package www.iesmurgi.habitwith.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Clase de datos que representa un ejercicio.
 * @param exercise_base Id de ejercicio.
 * @param name Nombre del ejercicio.
 * @param exerciseImage URL de la imagen del ejercicio.
 * @param description Descripción del ejercicio.
 */
data class Ejercicio(

    val exercise_base: String? = "",
    val name: String? = "",
    val exerciseImage: String? = "",
    val description: String? = ""

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(exercise_base)
        parcel.writeString(name)
        parcel.writeString(exerciseImage)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ejercicio> {
        override fun createFromParcel(parcel: Parcel): Ejercicio {
            return Ejercicio(parcel)
        }

        override fun newArray(size: Int): Array<Ejercicio?> {
            return arrayOfNulls(size)
        }
    }
}
