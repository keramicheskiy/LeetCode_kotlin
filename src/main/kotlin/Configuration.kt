import com.sun.nio.file.SensitivityWatchEventModifier
import java.awt.Color


data class Configuration (
    val latency: Int,
    val userColor: Color,
    val sensitivity: Int,
    val userName: String
)

class ConfigurationBuilder() {
    var latency: Int = 300
    var userColor: Color = Color.BLUE
    var sensitivity: Int = 900
    var userName: String = "user"

    fun setSensitivity(value: Int) : ConfigurationBuilder {
        this.sensitivity = value
        return this
    }

    fun setUserColor(value : Color) : ConfigurationBuilder {
        this.userColor = value
        return this
    }

    fun setLatency(value: Int) : ConfigurationBuilder {
        this.latency = value
        return this
    }

    fun setUserName(value: String) : ConfigurationBuilder {
        this.userName = value
        return this
    }

    fun build() : Configuration {
        return Configuration(
            latency = latency,
            sensitivity = sensitivity,
            userColor = userColor,
            userName = userName
        )
    }
}