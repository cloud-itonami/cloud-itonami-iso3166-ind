(ns statute.facts
  "General-law compliance catalog for India (IND) -- extends this
  repo's existing `marketentry.facts` (narrow public-procurement
  scope) with a second, orthogonal catalog of statutes a company
  generally must track for compliance. Mirrors
  cloud-itonami-iso3166-jpn/-usa/-esp/-swe/-nor/-dnk/-fin/-prt/-bel/-bra/-mex/-chl/-arg/-zaf/-col/-ury/-cri/-pan/-ecu/-pry/-gtm/-hnd's
  `statute.facts` (ADR-2607141700, cloud-itonami-compliance-fact-federation).

  Reuses this session's already-verified capital/organization data
  from cloud-itonami-municipality-ind-new-delhi (India Q668, New Delhi
  Q987, no P36 historical-capital bug).

  meity.gov.in (the Ministry of Electronics and IT's own portal, the
  natural primary source for the Digital Personal Data Protection
  Act) and mca.gov.in (the Ministry of Corporate Affairs' own portal,
  the natural primary source for the Companies Act) both returned HTTP
  403. Both entries here instead cite prsindia.org (PRS Legislative
  Research, a highly-reputable NON-governmental Indian legislative
  research body -- NOT itself an official government source) mirrors
  that turn out to be exact reproductions of the official Gazette of
  India publication: the Companies Act PDF rendered its full Gazette
  of India masthead (Ministry of Law and Justice, Legislative
  Department, government emblem) and title fully legibly; the DPDP
  Act PDF's Gazette masthead and date line ('New Delhi ... August 11,
  2023') rendered legibly but its Devanagari/English title text was
  garbled by font-subsetting -- the exact citation ('Act No. 22 of
  2023') is instead corroborated by the WebSearch tool's own indexed
  title snippet of the same document plus multiple independent citing
  sources (Wikipedia, AO Shearman, Future of Privacy Forum, OneTrust),
  all agreeing with the legible Gazette date. Because the underlying
  document content genuinely IS the official Gazette of India text
  (not a private paraphrase), both are tagged
  :official-gazette-of-india-prsindia-mirror rather than a lesser
  provenance tag.

  A law not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of statute entries."
  {"IND"
   [{:statute/id "ind.companies-act-2013"
     :statute/title "The Companies Act, 2013"
     :statute/jurisdiction "IND"
     :statute/kind :law
     :statute/law-number "Act No. 18 of 2013"
     :statute/url "https://prsindia.org/files/bills_acts/acts_parliament/2013/companies-act,-2013.pdf"
     :statute/url-provenance :official-gazette-of-india-prsindia-mirror
     :statute/enacted-date "2013-08-29"
     :statute/retrieved-at "2026-07-16"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "ind.digital-personal-data-protection-act-2023"
     :statute/title "The Digital Personal Data Protection Act, 2023"
     :statute/jurisdiction "IND"
     :statute/kind :law
     :statute/law-number "Act No. 22 of 2023"
     :statute/url "https://prsindia.org/files/bills_acts/acts_parliament/2023/Digital_Personal_Data_Protection_Act,_2023.pdf"
     :statute/url-provenance :official-gazette-of-india-prsindia-mirror
     :statute/enacted-date "2023-08-11"
     :statute/retrieved-at "2026-07-16"
     :statute/topic #{:data-protection :privacy}}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-ind statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "IND")) " IND statutes seeded with "
                 "official Gazette of India (mirrored via prsindia.org) citations. "
                 "Extend `statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
