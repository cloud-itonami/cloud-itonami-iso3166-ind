(ns marketentry.facts "India market-entry catalog.")
(def catalog
  {"IND" {:name "Republic of India"
          :owner-authority "GeM / CPPP central public procurement"
          :legal-basis "General Financial Rules; GeM terms"
          :national-spec "GeM seller registration + GSTIN / PAN"
          :provenance "https://gem.gov.in/"
          :required-evidence ["GSTIN/PAN record"
                              "GeM seller registration record"
                              "MCA company registration extract"
                              "Authorized-representative record"]
          :rep-owner-authority "GeM / procuring entities"
          :rep-legal-basis "Indian entity registration typically required for GeM seller onboarding"
          :rep-provenance "https://gem.gov.in/"
          :corporate-number-owner-authority "GSTN / MCA"
          :corporate-number-legal-basis "GSTIN / CIN"
          :corporate-number-provenance "https://www.gst.gov.in/"}
   "USA" {:name "United States" :owner-authority "GSA/SAM.gov" :legal-basis "FAR"
          :national-spec "SAM.gov" :provenance "https://sam.gov/"
          :required-evidence ["EIN record" "SAM.gov registration record" "State business registration record" "SAM UEI verification record"]}
   "JPN" {:name "Japan" :owner-authority "GEPS" :legal-basis "unified qualification"
          :national-spec "GEPS" :provenance "https://www.chotatujoho.go.jp/va/com/ShikakuTop.html"
          :required-evidence ["法人番号確認記録" "全省庁統一資格申請記録" "GEPS 事業者登録記録" "日本居住代理人確認記録"]}
   "AUS" {:name "Australia" :owner-authority "AusTender" :legal-basis "CPRs"
          :national-spec "AusTender" :provenance "https://www.tenders.gov.au/"
          :required-evidence ["ABN record" "AusTender registration record" "GST registration record" "Authorized-representative record"]}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
