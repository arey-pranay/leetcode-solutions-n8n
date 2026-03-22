# ✅ Min Operations to Reduce Integer (Binary Greedy)

## 🔹 Two-Liner (Ultra-Dense)

* Convert to binary and process from LSB → MSB, grouping consecutive `1`s and deciding whether to **add (carry)** or **subtract** to minimize operations.
* If a block of `1`s is length 1 → subtract (cost 1), if >1 → add (create carry, cost 1 now + future effect), track using `cont`.

---

## 🔹 Extra Pattern Insight (Binary Greedy + Carry Simulation)

This is a **bitwise greedy with carry propagation** problem.

Key idea:

> Sometimes increasing the number (adding 1) is cheaper than decreasing because it collapses long runs of `1`s.

Example:

```id="4h2k1p"
111 → better to add → 1000 (1 step + carry effect)
```

Interview twist:

* Direct integer manipulation without string.
* Use bit operations (`n & 1`, `n >> 1`).
* Classic variant: reduce to 1 with min steps.

---

## 🔹 Detailed Understanding

### Core Logic

You scan binary from right to left:

* Count consecutive `1`s (`cont`)
* When you hit `0`:

  * If `cont == 1` → just remove that `1` → +1 op
  * If `cont > 1` → better to **add 1** → flip this `0` to `1` (carry), reduce future cost

At end:

* If one `1` left → +1
* If multiple → +2 (one add + one final reduce)

---

### Why Greedy Works

Compare:

```id="gk39dl"
...0111 → subtracting → many steps
...0111 → adding → ...1000 (huge simplification)
```

So:

* Single `1` → remove
* Multiple `1`s → propagate carry

---

### Complexity

* **Time:** O(log N)
* **Space:** O(log N) (string) → can be O(1) using bits

---

## 🔹 Things To Remember

* This is **not simple bit counting** — it’s about **runs of 1s**.
* Carry propagation is the core trick.
* Edge case:

  * `n = 1`
  * All 1s like `111...`
* Modifying string (`sb.setCharAt`) simulates carry.

---

# 🧠 Pattern Summary of This Batch

| Problem        | Core Pattern                      |
| -------------- | --------------------------------- |
| Min Operations | Binary greedy + carry propagation |

### Meta Insight:

> Whenever operations involve `+1` and `/2` or binary transitions →
> **Think in binary and simulate carry instead of decimal thinking.**

---

If you master this pattern, you unlock a whole class of problems like:

* Integer replacement
* Min steps to reduce number
* Bit manipulation with greedy decisions

---

Send next. You're building a very solid pattern library now. 🚀
