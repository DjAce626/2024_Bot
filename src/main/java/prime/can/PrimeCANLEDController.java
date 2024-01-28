package prime.can;

import edu.wpi.first.wpilibj.CAN;

public class PrimeCANLEDController extends CAN {
    private static final byte kDeviceType = 10; // Miscellaneous
    private static final byte kDeviceManufacturer = 8; // Team Use

    private enum APIIDs {
        ACK,
        SetState,
        SetBrightness,
        SetColor,
        SetSpeed,
        SetPattern
    };

    public enum LEDPattern {
        Solid,
        Blink,
        RaceForward,
        RaceBackward,
        Pulse
    };

    private byte[][] m_lastPackets = new byte[4][];

    public PrimeCANLEDController(int deviceId) {
        super(deviceId);
    }

    /**
     * Write a packet to the LED controller containing the new state of the LEDs
     * @param stripNum
     * @param r
     * @param g
     * @param b
     * @param brightness
     * @param pattern
     * @param speed
     */
    public void setState(byte stripNum, byte r, byte g, byte b, byte brightness, byte pattern, byte speed) {
        if (stripNum < 0 || stripNum > 3) {
            System.out.println("Invalid strip number");
            return;
        }

        var packet = new byte[] {
            stripNum,
            r,
            g,
            b,
            brightness,
            pattern,
            speed
        };

        // Write the data packet to the controller
        writePacket(packet, APIIDs.SetState.ordinal());

        // Verify that the controller acknowledged the packet
        if (!readPacketTimeout(APIIDs.ACK.ordinal(), 20, null)) {
            System.out.println("LED Controller did not respond to SetState");
        } else {
            // Save the last packet sent for this strip
            m_lastPackets[stripNum] = packet;
        }
    }

    public void setColor(byte strip, byte r, byte g, byte b) {
        setState(strip, 
            r, 
            g, 
            b, 
            m_lastPackets[strip][4], 
            m_lastPackets[strip][5], 
            m_lastPackets[strip][6]);
    }

    public void setColor(byte strip, int color) {
        // Extract the RGB components from the color
        var r = ((color >> 16) & 0xFF);
        var g = ((color >> 8) & 0xFF);
        var b = (color & 0xFF);

        setColor(strip, (byte)r, (byte)g, (byte)b);
    }

    public void setColor(byte strip, Color color) {
        setColor(strip, color.getIntColor());
    }

    public void setBrightness(byte strip, byte brightness) {
        setState(strip, 
            m_lastPackets[strip][1], 
            m_lastPackets[strip][2], 
            m_lastPackets[strip][3], 
            brightness, 
            m_lastPackets[strip][5], 
            m_lastPackets[strip][6]);
    }

    public void setPattern(byte strip, LEDPattern pattern) {
        setState(strip, 
            m_lastPackets[strip][1], 
            m_lastPackets[strip][2], 
            m_lastPackets[strip][3], 
            m_lastPackets[strip][4], 
            (byte)pattern.ordinal(), 
            m_lastPackets[strip][6]);
    }

    public void setSpeed(byte strip, byte speed) {
        setState(strip, 
            m_lastPackets[strip][1], 
            m_lastPackets[strip][2], 
            m_lastPackets[strip][3], 
            m_lastPackets[strip][4], 
            m_lastPackets[strip][5], 
            speed);
    }
}
