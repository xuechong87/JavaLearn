package com.luckystars.tests;

import org.checkerframework.checker.units.qual.Temperature;
import org.junit.Test;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;
import oshi.util.platform.mac.SmcUtil;

import java.util.ArrayList;
import java.util.List;

public class SMC {

    @Test
    public void getCpuTemp() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hal = systemInfo.getHardware();
        OperatingSystem os = systemInfo.getOperatingSystem();

        // 获取操作系统信息
        System.out.println("操作系统：" + os.getFamily() + " " + os.getVersionInfo());

        // 获取 CPU 信息
        CentralProcessor processor = hal.getProcessor();
        System.out.println("CPU：" + processor.getProcessorIdentifier().getName());
        System.out.println("CPU 核心数：" + processor.getLogicalProcessorCount());

        // 获取内存信息
        GlobalMemory memory = hal.getMemory();
        System.out.println("总内存：" + memory.getTotal() / (1024 * 1024) + " MB");
        System.out.println("可用内存：" + memory.getAvailable() / (1024 * 1024) + " MB");

        // 获取磁盘信息 (示例，更多信息请参考 oshi 文档)
        hal.getDiskStores().forEach(disk -> {
            System.out.println("磁盘：" + disk.getName() + " 容量：" + disk.getSize() / (1024 * 1024 * 1024) + " GB");
        });

        System.out.println("CPU 温度：" + hal.getSensors().getCpuTemperature() + " ℃");
        System.out.println("CPU 风扇：" + hal.getSensors().getFanSpeeds() + " RPM");
        System.out.println("CPU 电压：" + hal.getSensors().getCpuVoltage()+ " V");
        ;
        System.out.println("CPU 温度：" + SmcUtil.smcGetFloat(SmcUtil.smcOpen(),SmcUtil.SMC_KEY_CPU_TEMP) + " ℃");
        SystemInfo si = new SystemInfo();
        System.out.println("CPU 温度：" + new SystemInfo().getHardware().getSensors().getCpuTemperature() + " ℃");
//        si.getHardware().getSensors().getCpuTemperature()

        // 获取进程信息 (示例)
//        os.getProcesses().forEach(process -> {
//            System.out.println("进程：" + process.getName() + " PID：" + process.getProcessID());
//        });
    }
    public List<Double> getCpuTempList() {
        List<Double> temperatures = new ArrayList<>();
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hal = systemInfo.getHardware();
        CentralProcessor processor = hal.getProcessor();
        hal.getSensors().getCpuTemperature();
        // 获取温度传感器信息
//        List<Temperature> temps = hal.getSensors().getCpuTemperature();
//        for (Temperature temp : temps) {
//            // 过滤出与CPU相关的温度传感器
//            if (temp.getSensor().toLowerCase().contains("cpu")) {
//                temperatures.add(temp.getTemperature());
//            }
//        }

        return temperatures;
    }
}
