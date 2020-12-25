/*
 * Copyright (C) 2020 Muntashir Al-Islam
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.muntashirakon.AppManager.servermanager;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.RemoteException;

import java.util.List;

import androidx.annotation.NonNull;
import io.github.muntashirakon.AppManager.AppManager;
import io.github.muntashirakon.AppManager.server.common.CallerResult;
import io.github.muntashirakon.AppManager.server.common.Shell;
import io.github.muntashirakon.AppManager.server.common.ShellCaller;

public final class ApiSupporter {

    private ApiSupporter() {
    }

    public static List<PackageInfo> getInstalledPackages(int flags, int userHandle) throws RemoteException {
        return AppManager.getIPackageManager().getInstalledPackages(flags, userHandle).getList();
    }

    @NonNull
    public static PackageInfo getPackageInfo(String packageName, int flags, int userHandle) throws RemoteException {
        return AppManager.getIPackageManager().getPackageInfo(packageName, flags, userHandle);
    }

    @NonNull
    public static ApplicationInfo getApplicationInfo(String packageName, int flags, int userHandle) throws RemoteException {
        return AppManager.getIPackageManager().getApplicationInfo(packageName, flags, userHandle);
    }

    public static Shell.Result runCommand(String command) throws Exception {
        LocalServer localServer = LocalServer.getInstance();
        ShellCaller shellCaller = new ShellCaller(command);
        CallerResult callerResult = localServer.exec(shellCaller);
        return (Shell.Result) callerResult.getReplyObj();
    }
}
