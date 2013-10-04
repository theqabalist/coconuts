import subprocess

def bowl(s):
    proc = subprocess.Popen(["clj","bowling.clj",s],stdout=subprocess.PIPE,stderr=subprocess.PIPE)
    out, err = proc.communicate()
    assert err == ""
    return out.strip()
