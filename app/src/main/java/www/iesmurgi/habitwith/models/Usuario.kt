package www.iesmurgi.habitwith.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Clase que representa un usuario con sus datos personales y objetivos.
 * @param edad Edad del usuario.
 * @param altura Altura del usuario.
 * @param peso Peso del usuario.
 * @param sexo Sexo del usuario.
 * @param objetivo Objetivo del usuario (por ejemplo, perder peso, ganar masa muscular, etc.).
 */
data class Usuario(
    var edad: String? = "",
    var altura: String? = "",
    var peso: String? = "",
    var sexo: String? = "",
    var objetivo: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(edad)
        parcel.writeString(altura)
        parcel.writeString(peso)
        parcel.writeString(sexo)
        parcel.writeString(objetivo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }

        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }
}
