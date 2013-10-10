import subprocess

def bowl(s):
    proc = subprocess.Popen(["clj","bowling.clj",s],stdout=subprocess.PIPE,stderr=subprocess.PIPE)
    out, err = proc.communicate()
    assert err == ""
    return out.strip()

def inc_bowl(l):
    proc = subprocess.Popen(["clj","bowling.clj"],stdout=subprocess.PIPE,stderr=subprocess.PIPE,stdin=subprocess.PIPE)
    input_ = "\n".join(l.split())
    out, err = proc.communicate(input_)
    assert err == ""
    return out.strip()
